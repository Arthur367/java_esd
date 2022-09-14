/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esddesktop;

import java.util.Map;
import javax.swing.text.Element;

import org.dizitart.no2.Document;
import org.dizitart.no2.IndexOptions;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.objects.ObjectRepository;

/**
 *
 * @author netbot
 */
public class dbClass {
    
    public static void main(){
        
    }

    public static void CreateNitrite(Map<String, String> map) {
        Nitrite db = Nitrite.builder()
                .filePath("log.db").openOrCreate("user", "password");

        NitriteCollection collection = db.getCollection("log");
        

        int i = 0;

        Document document = Document.createDocument("Id", i);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            document.put(entry.getKey(), entry.getValue());
            collection.insert(document);
            i++;
        }

        collection.createIndex(
                "Id", IndexOptions.indexOptions(IndexType.Unique, true));
        for(var items: collection.find()){
            System.out.println(items);
        }
        System.out.println(collection.find());
    }
    
    public void findLog(){
       Nitrite db = Nitrite.builder()
                .filePath("log.db").openOrCreate("user", "password");
       
    }

}
