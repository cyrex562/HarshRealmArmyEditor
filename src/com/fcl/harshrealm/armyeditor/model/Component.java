package com.fcl.harshrealm.armyeditor.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "modules")
public class Component extends AbstractModule {
	// id
	@DatabaseField(id = true, generatedId = true)
	private int id;
	// name
	@DatabaseField(canBeNull = false)
	private String name;
	// parent component
	@DatabaseField(canBeNull = true, foreign = true)
	private Component parent;
	// sub components
	@ForeignCollectionField(eager = false)
	ForeignCollection<Component> subComponents;
	// component type
	@DatabaseField(canBeNull = false, foreign = true)
	private ComponentType componentType;
	// attributes
	@ForeignCollectionField(eager = false)
	ForeignCollection<Attribute> attributes;
	// rules
	@ForeignCollectionField(eager = false)
	ForeignCollection<Rule> rules;
	// descriptors
	@ForeignCollectionField(eager = false)
	ForeignCollection<Descriptor> descriptors;
	
	public Component() {
		
	}

}
