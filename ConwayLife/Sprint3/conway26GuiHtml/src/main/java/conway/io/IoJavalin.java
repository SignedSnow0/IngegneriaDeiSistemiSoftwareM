package conway.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.websocket.WsContext;
import main.java.conway.domain.Cell;
import main.java.conway.domain.GameController;
import main.java.conway.domain.Grid;
import main.java.conway.domain.IOutDev;
import main.java.conway.domain.LifeInterface;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.utils.CommUtils;

public class IoJavalin {
	public IoJavalin() {
        var app = Javalin.create(config -> {
			config.staticFiles.add(staticFiles -> {
				staticFiles.directory = "/page";
				staticFiles.location = Location.CLASSPATH; // Cerca dentro il JAR/Classpath
				/*
				 * i file sono "impacchettati" con il codice, non cercati sul disco rigido esterno.
				 */
		    });
		}).start(8080);
        
        app.get("/", ctx -> {
    		Path path = Path.of("./src/main/resources/page/ConwayInOutPage.html");   
		    if (Files.exists(path)) {
		        // Usiamo Files.newInputStream che è più moderno di FileInputStream
		        ctx.contentType("text/html").result(Files.newInputStream(path));
		    } else {
		        ctx.status(404).result("File non trovato nel file system");
		    }
		    //ctx.result("Hello from Java!"));  //la forma più semplice di risposta
        });
        
        app.ws("/eval", ws -> {
            ws.onConnect(ctx -> {
            	System.out.println("Client connected!");
            	context = ctx;
            });
            ws.onMessage(ctx -> {
                String message = ctx.message();
                var msg = new ApplMessage(message);
                System.out.println(msg.toString());

                if (msg.msgContent().equals("start")) {
                	outDev.getController().onStart();
                } else if (msg.msgContent().equals("stop")) {
                	outDev.getController().onStop();
                } else if (msg.msgContent().equals("clear")) {
                	outDev.getController().onClear();
                } else if (msg.msgContent().contains("cell")) {
	                Pattern p = Pattern.compile("cell\\((\\d+),\\s*(\\d+)\\)");
	                Matcher m = p.matcher(msg.msgContent());
	
	                if (m.find()) {
	                    int column = Integer.parseInt(m.group(1));
	                    int row = Integer.parseInt(m.group(2));
	                    
	                    System.out.println("Switched cell " + row + " " + column);
	                    outDev.getController().switchCellState(row, column);     
	                }
                }
            });
        });
	}
	
	public static void main(String[] args) {
		var resource = IoJavalin.class.getResource("/pages");
		CommUtils.outgreen("DEBUG: La cartella /page si trova in: " + resource);
		new IoJavalin();
	}

	public WsContext getContext() { return context; }
	public void setOutInWs(OutInWs ws) { outDev = ws; } 
	
	private OutInWs outDev;
	private WsContext context;
}
