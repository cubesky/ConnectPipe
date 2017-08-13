package cube.ScriptEngine;

import org.python.util.PythonInterpreter;


/**
 * py请传入文件
 * @author Max
 *
 */
public class PY {
	public static void DoPY(String py){
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(py);
	}
}
