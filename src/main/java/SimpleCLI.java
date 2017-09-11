import sources.Flavour;
import sources.Image;
import sources.occi.ROCCI;
import sources.occi.interfaces.Model;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleCLI {
	public static void main(String[] args) {
		FileReader fileReader = null;

		String modelString;
		try {
			modelString = new String(Files.readAllBytes(Paths.get("/home/majlen/cesnet/rOCCI-core/examples/rendering/model.txt")));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}

		try {
			ROCCI occi = new ROCCI(modelString);

			sources.Model occiModel = occi.model;

			for (Image i : occiModel.getServices().get(0).getAppliances()) {
				System.out.println(i);
			}

			for (Flavour f : occiModel.getServices().get(0).getFlavours()) {
				System.out.println(f);
			}
		} catch (IOException | ScriptException e) {
			System.out.println(e.getMessage());
		}
	}
}
