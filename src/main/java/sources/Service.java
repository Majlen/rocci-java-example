package sources;

import java.net.URI;
import java.util.List;

public class Service {
	//private String id;
	private String name;
	private String country;
	private URI endpoint;
	private String site_id;
	private String service_id;
	private List<Flavour> flavours;
	private List<Image> appliances;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public URI getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(URI endpoint) {
		this.endpoint = endpoint;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public List<Flavour> getFlavours() {
		return flavours;
	}

	public void setFlavours(List<Flavour> flavours) {
		this.flavours = flavours;
	}

	public List<Image> getAppliances() {
		return appliances;
	}

	public void setAppliances(List<Image> appliances) {
		this.appliances = appliances;
	}

	public String toString() {
		return name;
	}
}
