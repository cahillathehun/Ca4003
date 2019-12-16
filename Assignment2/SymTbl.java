/**
Symbol Table for ccal parser (Assignment 2)
AC
**/

import java.util.*;

public class SymTbl extends Object {
  private Hashtable<String, LinkedList<String>> ST;
  private Hashtable<String, String> types;  //hash table for the types
  private Hashtable<String, String> vals;   //hash table for the vals(eg var, const, func)

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

  public void getter(String id, String type, String scope){
    LinkedList<String> scopes = ST.get(scope);

    if(scopes == null){
      System.out.print("  Item " + id + " not in scope " + scope);
    }
  }

  public LinkedList<String> scopeGetter(String scope){
    //self explanatory
    return ST.get(scope);
  }

  public String typeGetter(String id, String scope){
    String type = types.get(id + scope);
    //check if not equal to null then print
    if(type != null){
      return type ;
    } else {
      //check in all of the prog for the id then print if not null
      type = types.get(id + "global");
      if(type != null){
        return type;
      }
    }
    return null;
  }

  public String valGetter(String id, String scope){
    //gets the vals
    String val = vals.get(id + scope);

    if(val != null){
      //return val if not null
      return(val);
    } else {
      //check global scope for val not equal null & return it
      val = vals.get(id + "global");

      if(val != null){
        return val;
      }
    }
    return null;
  }

  public boolean dupeChecker(String id, String scope){
    LinkedList<String> scopeLL = ST.get(scope);
    LinkedList<String> globalLL = ST.get("global");

    if(scope == "global"){
      return globalLL.indexOf(id) == globalLL.lastIndexOf(id);
    }

    return ((globalLL.indexOf(id) == -1) && (scopeLL.indexOf(id) == scopeLL.lastIndexOf(id)));
  }

  public ArrayList<String> varGetter(){
    ArrayList<String> varList = new ArrayList<String>();
    Enumeration items = ST.keys();

    while(items.hasMoreElements()){
      String scope = (String) items.nextElement();
      LinkedList<String> scopeList = ST.get(scope);
      int i = 0;
      int n = scopeList.size();

      while(i < n){
        String val = vals.get(scopeList.get(i) + scope);
        if(val == "var"){
          varList.add(scopeList.get(i));
        }
        i++;
      }
      i++;
    }

    return varList;
  }

  public void printST(){
    //printing method for Symbol Table
    String scope;
    Enumeration tbl = ST.keys();

    while (tbl.hasMoreElements()){
      scope = (String) tbl.nextElement();
      System.out.println("\n## scope " + scope + " ##");
      LinkedList<String> itm = ST.get(scope);

      for (String id : itm){
        //for each id in item
        String type = types.get(id + scope);
        String val = vals.get(id + scope);
        System.out.println("|-  VALUE: " + val + " ID: "+ id + " TYPE: " + type);
      }
    }
  }
}
