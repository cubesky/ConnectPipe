package cube.ScriptEngine;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JEngine {
	ScriptEngine engine;
	public ScriptEngine getEngine() {
		return engine;
	}
	public Invocable getIn() {
		return in;
	}
	Invocable in;
	public JEngine(String JPath) throws FileNotFoundException, ScriptException{
		engine = new ScriptEngineManager().getEngineByName("java");  
		engine.eval(new FileReader(JPath));
		in = (Invocable)engine;    
	}
}
