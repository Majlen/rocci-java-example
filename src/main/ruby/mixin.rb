require 'java'
require 'occi/core'
java_require 'mixin'
java_package 'sources.occi.interfaces'
java_import "sources.occi.interfaces.Mixin"
java_import "java.util.List"

class MixinImpl
	include Mixin
	java_implements Mixin

	def initialize(mixin)
		@mixin = mixin
	end

	java_signature 'List<Mixin> getRelations()'
	def relations()
		list = java.util.LinkedList.new
		@mixin.depends.each do |mixin|
			impl = MixinImpl.new(mixin)
			list.add(impl)
		end
		return list
	end

	java_signature 'String getTerm()'
	def term()
		return @mixin.term
	end

	java_signature 'String getTitle()'
	def title()
		return @mixin.title
	end

	java_signature 'String getSchema()'
	def schema()
		return @mixin.schema
	end
end
