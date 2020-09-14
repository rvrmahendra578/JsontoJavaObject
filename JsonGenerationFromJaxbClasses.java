package T;


import static java.lang.System.err;
import static java.lang.System.out;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;


public class JsonGenerationFromJaxbClasses {

	
	 private ObjectMapper createJaxbObjectMapper()
	   {
	      final ObjectMapper mapper = new ObjectMapper();
	      final TypeFactory typeFactory = TypeFactory.defaultInstance();
	      final AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(typeFactory);
	      // make deserializer use JAXB annotations (only)
	      mapper.getDeserializationConfig().with(introspector);
	      // make serializer use JAXB annotations (only)
	      mapper.getSerializationConfig().with(introspector);
	      return mapper;
	   }
	 
	/* private void writeToStandardOutputWithDeprecatedJsonSchema(
		      final ObjectMapper mapper, final String fullyQualifiedClassName)
		   {
		      try
		      {
		         final JsonSchema jsonSchema = mapper.generateJsonSchema(Class.forName(fullyQualifiedClassName));
		         out.println(jsonSchema);
		      }
		      catch (ClassNotFoundException cnfEx)
		      {
		         err.println("Unable to find class " + fullyQualifiedClassName);
		      }
		      catch (JsonMappingException jsonEx)
		      {
		         err.println("Unable to map JSON: " + jsonEx);
		      }
		   }
	 */
	 
	 private void writeToStandardOutputWithModuleJsonSchema(
			   final String fullyQualifiedClassName)
			{
			   final SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
			   final ObjectMapper mapper = new ObjectMapper();
			   try
			   {
			      mapper.acceptJsonFormatVisitor(mapper.constructType(Class.forName(fullyQualifiedClassName)), visitor);
			      final com.fasterxml.jackson.module.jsonSchema.JsonSchema jsonSchema = visitor.finalSchema();
			      out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema));
			      
			      File file = new File("IP_test12.txt"); 
		            FileWriter fileWriter;
					try {
						fileWriter = new FileWriter(file);
						fileWriter.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema));
		       			  
		       			  fileWriter.flush(); fileWriter.close(); 
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
		            
			   }
			   catch (ClassNotFoundException cnfEx)
			   {
			      err.println("Unable to find class " + fullyQualifiedClassName);
			   }
			   catch (JsonMappingException jsonEx)
			   {
			      err.println("Unable to map JSON: " + jsonEx);
			   }
			   catch (JsonProcessingException jsonEx)
			   {
			      err.println("Unable to process JSON: " + jsonEx);
			   }
			}
	 
	 public static void main(String[] arguments)
	   {
	     /* if (arguments.length < 1)
	      {
	         err.println("Need to provide the fully qualified name of the highest-level Java class with JAXB annotations.");
	         System.exit(-1);
	      }
	      */
	      final JsonGenerationFromJaxbClasses instance = new JsonGenerationFromJaxbClasses();
	    //  final String fullyQualifiedClassName = "com.phaseforward.informadapter1379.response.ResponseODM";
	      final String fullyQualifiedClassName = "com.safety.ReportDrugSafetyReportEBMType";
	      final ObjectMapper objectMapper = instance.createJaxbObjectMapper();
	     // instance.writeToStandardOutputWithDeprecatedJsonSchema(objectMapper, fullyQualifiedClassName);
	      instance.writeToStandardOutputWithModuleJsonSchema(fullyQualifiedClassName);
	      
	   }
}
