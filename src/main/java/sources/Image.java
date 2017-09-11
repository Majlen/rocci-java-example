package sources;

import java.net.URI;

public class Image {
	private URI id;
	private String name;
	private String key;
	private int appDBID;
	private Service service;

	public URI getId() {
		return id;
	}

	public void setId(URI id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getAppDBID() {
		return appDBID;
	}

	public void setAppDBID(int appDBID) {
		this.appDBID = appDBID;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		if (appDBID != 0) {
			return appDBID;
		} else {
			return name.hashCode();
		}
	}

	@Override
	public boolean equals(Object object) {
		if (object != null && object instanceof Image) {
			if (appDBID != 0) {
				if (this.appDBID == ((Image) object).appDBID) {
					return true;
				}
			} else {
				if (this.name.equals(((Image) object).name)) {
					return true;
				}
			}
		}

		return false;
	}
}
