package sources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Model {
	private List<Service> services;
	private Map<String, List<Image>> images;

	public Model(List<Service> services, Map<String, List<Image>> images) {
		this.services = services;
		this.images = images;
	}

	public Model(Service service, List<Image> images) {
		services = new LinkedList<>();
		services.add(service);

		this.images = new HashMap<>();
		images.forEach(image -> {
			if (!this.images.containsKey(image.getId().toString())) {
				this.images.put(image.getId().toString(), new LinkedList<>());
			}

			List<Image> list = this.images.get(image.getId().toString());
			list.add(image);
		});
	}

	public List<Service> getServices() {
		return services;
	}

	public Map<String, List<Image>> getImages() {
		return images;
	}
}
