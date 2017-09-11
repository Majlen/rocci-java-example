package sources;

import java.net.URI;

public class Flavour {
	private String name;
	private String title;
	private URI id;
	private int memory;
	private int vcpu;
	private int cpu;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public URI getId() {
		return id;
	}

	public void setId(URI id) {
		this.id = id;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public int getVcpu() {
		return vcpu;
	}

	public void setVcpu(int vcpu) {
		this.vcpu = vcpu;
	}

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public String toString() {
		if (title != null) {
			return title;
		} else {
			return "CPU: " + cpu + "/" + vcpu + ", memory: " + memory + " MB";
		}
	}
}
