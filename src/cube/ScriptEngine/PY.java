package cube.ScriptEngine;

import org.python.util.PythonInterpreter;


/**
 * py�봫���ļ�
 * @author Max
 *
 */
public class PY {
	public static void DoPY(String py){
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(py);
	}
}
