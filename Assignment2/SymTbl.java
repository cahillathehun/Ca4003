/**
Symbol Table for ccal parser (Assignment 2)
AC
**/

import java.util.*;

public class SymTbl extends Object {
  private Hashtable<String, LinkedList<String>> ST;
  private Hashtable<String, String> types;
  private Hashtable<String, String> vals;

  SymTbl(){
    ST = new Hashtable<>();
    types = new Hashtable<>();
    vals = new Hashtable<>();
    ST.insert("global", new LinkedList<>());
  }

  public void insert(String id, String type, String val, String scope){
    LinkedList<String> item = ST.get(scope);

    if(item == null){
      item = new LinkedList<>();
      item.add(id);
      ST.insert(scope, id);
    } else {
      item.addFirst(id);
    }
    types.insert(id + scope, type);
    vals.insert(id + scope, val);
  }

  public void printST(){
    String scope;
    Enumeration tbl = ST.keys();

    while (tbl.hasMoreElements()){
      scope = (String) tbl.nextElement();
      System.out.println("("+"scope :"+scope+")");
      LinkedList<String> item = ST.get(scope);

      for (String id : item){
        //for each id in item
        String type = type.get(id + scope);
        String val = vals.get(id + scope);
        System.out.println("(val: " + val + " id: "+ id + " type: " + type + ")");
      }
    }
  }
}
