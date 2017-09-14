package sources.occi;

import sources.Flavour;
import sources.Image;
import sources.Service;
import cz.cesnet.cloud.occi.interfaces.Mixin;
import cz.cesnet.cloud.occi.interfaces.Model;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

public class ROCCI {
	public sources.Model model;

	public ROCCI(String file) throws IOException, ScriptException {
		Model occiModel = Model.getModel(file);
		Service service = new Service();
		service.setEndpoint(URI.create("http://localhost/test"));
		service.setName("Test endpoint");

		List<Flavour> flavours = new LinkedList<>();
		List<Image> images = new LinkedList<>();

		for (Mixin os: occiModel.getOsTpls()) {
			Image i = new Image();
			i.setName(os.getTitle());
			i.setId(URI.create(os.getSchema() + os.getTerm()));
			i.setKey(os.getSchema() + os.getTerm());
			images.add(i);
		}

		for (Mixin res: occiModel.getResourceTpls()) {
			Flavour f = new Flavour();
			f.setName(res.getTerm());
			f.setId(URI.create(res.getSchema() + res.getTerm()));
			f.setTitle(res.getTitle());
			flavours.add(f);
		}

		service.setAppliances(images);
		service.setFlavours(flavours);

		this.model = new sources.Model(service, images);

	}
}
