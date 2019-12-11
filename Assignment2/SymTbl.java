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
    //sets the default scope to global
    ST.put("global", new LinkedList<String>());
  }

  public void insert(String id, String type, String val, String scope){
    //methos for adding items into the Symbol Table
    LinkedList<String> itm = ST.get(scope);

    if(itm == null){
      itm = new LinkedList<>();
      itm.add(id);
      ST.put(scope, itm);
    } else {
      itm.addFirst(id);
    }
    types.put(id + scope, type);
    vals.put(id + scope, val);
  }

  public void printST(){
    //printing method for Symbol Table
    String scope;
    Enumeration tbl = ST.keys();

    while (tbl.hasMoreElements()){
      scope = (String) tbl.nextElement();
      System.out.println("("+"scope :"+scope+")");
      LinkedList<String> itm = ST.get(scope);

      for (String id : itm){
        //for each id in item
        String type = types.get(id + scope);
        String val = vals.get(id + scope);
        System.out.println("(val: " + val + " id: "+ id + " type: " + type + ")");
      }
    }
  }
}
