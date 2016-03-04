package umlparser.src.classes;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.type.Type;

public class SearchAllFiles {

	public LinkedHashMap<String, List<BodyDeclaration>> scanFile(List<File> files) throws Exception{
		LinkedHashMap<String,List<BodyDeclaration>> members_map = new LinkedHashMap<>();
		for (File file : files) {
			FileInputStream fis = new FileInputStream(file);
			
			CompilationUnit cu;
			
			try {
				cu = JavaParser.parse(file);
			}finally {
					fis.close();
			}
		
			members_map.put(file.getName(), createBoxes(cu));
		}
		return members_map;
	}
	

	private static List<BodyDeclaration> createBoxes(CompilationUnit cu) {
		List<TypeDeclaration> typeValues = cu.getTypes();
		System.out.print(typeValues);
		List<BodyDeclaration> bodyList = typeValues.get(0).getMembers();
		Map<VariableDeclarator, Type> fieldMap = upperBox(bodyList);
		Iterator<?> itField = fieldMap.entrySet().iterator();
	    while (itField.hasNext()) {
	       Map.Entry<?, ?> pair = (Map.Entry<?, ?>)itField.next();
	       
	      // System.out.println(pair.getValue());
	    }
		//System.out.println(fieldMap);
		Map<String, Type> methodMap = lowerBox(bodyList);
		//System.out.println(methodMap);
		return bodyList;
	}
	private static LinkedHashMap<VariableDeclarator, Type> upperBox(List<BodyDeclaration> bodyMembersList){
		LinkedHashMap<VariableDeclarator, Type> fields = new LinkedHashMap<>();
		for(BodyDeclaration bodyMember : bodyMembersList){
			if(bodyMember instanceof FieldDeclaration){
				FieldDeclaration field = (FieldDeclaration) bodyMember;
				
					fields.put(field.getVariables().get(0), field.getType());
				
			}
		}
		return fields;
	}
	private static LinkedHashMap<String, Type> lowerBox(List<BodyDeclaration> bodyMembersList){
		LinkedHashMap<String, Type> methods = new LinkedHashMap<>();
		for(BodyDeclaration bodyMember : bodyMembersList){
			if(bodyMember instanceof MethodDeclaration){
				MethodDeclaration method = (MethodDeclaration) bodyMember;
				
					methods.put(method.getName(), method.getType());
				
			}
		}
		return methods;
	}
}
//	public void boxValues(List<TypeDeclaration> typeValues) {
//		for (TypeDeclaration types : typeValues){
//			List<BodyDeclaration> bodyMembersList = types.getMembers();
//			
//			for(BodyDeclaration bodyMember : bodyMembersList){
//				if(bodyMember instanceof FieldDeclaration){
//										
//				}
//				else if(bodyMember instanceof MethodDeclaration){
//					MethodDeclaration method = (MethodDeclaration) bodyMember;
//					if(!(method.getName().contains("get")||method.getName().contains("set"))){
//						methods.put(method.getName(), method.getType());
//					}
//				}
//			}			
//		}
	//}
//}

