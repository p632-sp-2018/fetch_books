package service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;


@Service
public class GraphQLService {
	
	@Value("classpath:books.graphql")
	Resource resource;
	
	private GraphQL graphQL;
	
	//load schema at application start up
	@PostConstruct
	public void loadschema() throws IOException {
		//get the schema
		File schemaFile = resource.getFile();
		//parse schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		setGraphQL(GraphQL.newGraphQL(schema).build());
		
		
		
	}

	private RuntimeWiring buildRuntimeWiring() {
		return null;
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}

	public void setGraphQL(GraphQL graphQL) {
		this.graphQL = graphQL;
	}

	

}
