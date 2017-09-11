package sources.occi.interfaces;

import javax.script.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public interface Model {
	List<Mixin> getMixins();
	List<Mixin> getResourceTpls();
	List<Mixin> getOsTpls();

	static Model getModel(String model) throws IOException, ScriptException {
		ScriptEngineManager m = new ScriptEngineManager();
		ScriptEngine rubyEngine = m.getEngineByName("jruby");
		ScriptContext context = rubyEngine.getContext();
		context.setAttribute("model", model, ScriptContext.ENGINE_SCOPE);

		InputStream is = Model.class.getResourceAsStream("/model.rb");
		InputStreamReader file = new InputStreamReader(is);
		Object receiver = rubyEngine.eval(file);
		is.close();
		file.close();

		return ((Invocable)rubyEngine).getInterface(receiver, Model.class);
	}
}
