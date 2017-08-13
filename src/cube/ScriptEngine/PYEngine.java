package cube.ScriptEngine;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class PYEngine {
	ScriptEngine engine;
	public ScriptEngine getEngine() {
		return engine;
	}
	public Invocable getIn() {
		return in;
	}
	Invocable in;
	public PYEngine(String PYPath) throws FileNotFoundException, ScriptException{
		engine = new ScriptEngineManager().getEngineByName("python");  
		engine.eval(new FileReader(PYPath));
		in = (Invocable)engine;
	}
}
