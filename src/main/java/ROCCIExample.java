import sources.Flavour;
import sources.Image;
import sources.occi.ROCCI;
import sources.occi.interfaces.Model;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ROCCIExample {
	public static void main(String[] args) {
		String modelString;
		try {
			BufferedInputStream is = new BufferedInputStream(Model.class.getResourceAsStream("/model.txt"));
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			int result = is.read();
			while (result != -1) {
				out.write((byte) result);
				result = is.read();
			}
			modelString = out.toString(StandardCharsets.UTF_8.name());
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
