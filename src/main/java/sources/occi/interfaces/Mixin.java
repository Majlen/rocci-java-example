package sources.occi.interfaces;

import java.util.List;

public interface Mixin {
	List<Mixin> getRelations();
	String getTerm();
	String getSchema();
	String getTitle();
}
