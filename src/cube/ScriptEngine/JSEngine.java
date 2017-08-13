package cube.ScriptEngine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSEngine {
	ScriptEngine engine;
	public JSEngine(){}
	public ScriptEngine getEngine() {
		return engine;
	}
	public Invocable getIn() {
		return in;
	}
	Invocable in;
	public JSEngine(String JSPath) throws FileNotFoundException, ScriptException{
		engine = new ScriptEngineManager().getEngineByName("javascript");  
		engine.eval(new FileReader(JSPath));
		in = (Invocable)engine;    
	}
	public JSEngine(Reader JSPath) throws FileNotFoundException, ScriptException{
		engine = new ScriptEngineManager().getEngineByName("javascript");  
		engine.eval(JSPath);
		in = (Invocable)engine;    
	}
}
