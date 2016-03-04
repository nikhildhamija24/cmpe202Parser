package umlparser.src.classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import japa.parser.ast.body.BodyDeclaration;

public class SearchClasses {	
	public static void main(String[] args) throws Exception{
		File file = new File("D:\\Grad School\\SSE-1Sem\\uml-parser-test\\uml-parser-test-1");
		//File file = new File("D:\\Grad School\\SSE-1Sem\\uml-parser-test\\");
		List<File> javaFiles = new ArrayList<>();
		searchJavaFiles(file, javaFiles);
						
		SearchAllFiles searchFiles = new SearchAllFiles();
		LinkedHashMap<String,List<BodyDeclaration>> members_map = searchFiles.scanFile(javaFiles);
		//System.out.println(members_map);
		 Iterator<?> it = members_map.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<?, ?> pair = (Map.Entry<?, ?>)it.next();
		        //System.out.println(pair.getKey().toString() + " = " + pair.getValue());
		        //it.remove();
		    }
	}
	
	private static void searchJavaFiles(File root, List<File> javaFiles){
		if(root == null || javaFiles == null){
			return;
		}
		if(root.isDirectory()){
			for(File file : root.listFiles()){
				searchJavaFiles(file, javaFiles);
			}
		}else if(root.isFile() && root.getName().endsWith(".java")){
			javaFiles.add(root);
		}			
	}
}
	
//	private static List<File> getListOfFiles(String path){
//		File directory = new File(path);	
//		File [] files = directory.listFiles(new FilenameFilter() {
//			
//			@Override
//			public boolean accept(File dir, String name) {
//				return name.endsWith(".java");
//			}
//		});
//		List<File> filesList = new ArrayList<File>(Arrays.asList(files)) ;
//		return filesList;		
//	}

