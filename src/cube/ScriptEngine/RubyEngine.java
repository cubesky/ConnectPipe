package cube.ScriptEngine;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RubyEngine {
	ScriptEngine engine;
	public ScriptEngine getEngine() {
		return engine;
	}
	public Invocable getIn() {
		return in;
	}
	Invocable in;
	public RubyEngine(String RubyPath) throws FileNotFoundException, ScriptException{
		engine = new ScriptEngineManager().getEngineByName("ruby");  
		engine.eval(new FileReader(RubyPath));
		in = (Invocable)engine;
	}
}
