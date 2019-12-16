import java.util.*;

public class semanticCheckVisitor implements ccalparseVisitor{
  static String scope = "global";   //set default scope to global
  static HashSet<String> funcs = new HashSet<>();
  static HashSet<String> vars = new HashSet<>();
  static Hashtable<String, LinkedHashSet<String>> dupes = new Hashtable<>();
  static SymTbl ST;

  static int e_count = 0;

  public static void checkDupes(String id, String scope){

    if(!ST.dupeChecker(id, scope)){
      HashSet<String> dupe = dupes.get(scope);

      if(dupe != null){
        dupe.add(id);
      } else {
        LinkedHashSet<String> find = new LinkedHashSet<>();

        find.add(id);
        dupes.put(scope, find);
      }
    }
  }

  public static void useChecker(){
    ArrayList<String> varList = ST.varGetter();
    int i = 0;
    int n = varList.size();

    while(i < n){

      if(!vars.contains(varList.get(i))){
        System.out.println("WARNING: " + varList.get(i) + " IS DECLARED BUT NOT USED!");
        e_count++;
        i++;
      }
    }
  }



  public Object visit(SimpleNode node, Object data){
    throw new RuntimeException("Visit SimpleNode");
  }

  public Object visit(Program node, Object data){
    ST = (SymTbl) data;

    int n = node.jjtGetNumChildren();
    int i = 0;
    while(i < n){
      node.jjtGetChild(i).jjtAccept(this, data);
      i++;
    }

    if(e_count != 0){
      System.out.println(e_count + "ERRORS!");
    } else {
      System.out.println("No errors :)");
    }

    return data;
  }

  public Object visit(Main node, Object data){
    this.scope = "Main";
    int n = node.jjtGetNumChildren();
    int i = 0;

    while(i < n){
      node.jjtGetChild(i).jjtAccept(this, data);
      i++;
    }

    return data;
  }

  public Object visit(Decl node, Object data){
    node.childrenAccept(this, data);
    return node.value;
  }

  public Object visit(VarDecl node, Object data){
    String type = (String)node.jjtGetChild(1).jjtAccept(this, data);
    String id = (String)node.jjtGetChild(0).jjtAccept(this, data);

    return data;
  }

  public Object visit(ConstDecl node, Object data){
    String type = (String)node.jjtGetChild(1).jjtAccept(this, data);
    String id = (String)node.jjtGetChild(0).jjtAccept(this, data);

    return data;
  }

  public Object visit(Type node, Object data){
    return node.value;
  }

  public Object visit(Num node, Object data){
    return node.value;
  }

  public Object visit(Digit node, Object data){
    return node.value;
  }

  public Object visit(TrueBool node, Object data){
    return node.value;
  }

  public Object visit(FalseBool node, Object data){
    return node.value;
  }

  public Object visit(FunctionCall node, Object data){
    return node.value;
  }




  public Object visit(ID node, Object data){
    return node.value;
  }



  public Object visit(Assignment node, Object data){
    return data;
  }

  public Object visit(MinusAssignment node, Object data){
    return data;
  }

  public Object visit(PlusOp node, Object data){
    return " + ";
  }

  public Object visit(MinusOp node, Object data){
    return " - ";
  }

  public Object visit(Compare node, Object data){
    node.childrenAccept(this, data);
    return node.value;
  }

  public Object visit(EqCmpOp node, Object data){
    return node.value;
  }

  public Object visit(NotEqCmpOp node, Object data){
    return node.value;
  }

  public Object visit(LTCmpOp node, Object data){
    return node.value;
  }

  public Object visit(LTEqCmpOp node, Object data){
    return node.value;
  }

  public Object visit(GTCmpOp node, Object data){
    return node.value;
  }

  public Object visit(GTEqCmpOp node, Object data){
    return node.value;
  }

  public Object visit(Function node, Object data){
    return node.value;
  }

  public Object visit(Return node, Object data){
    return node.value;
  }

  public Object visit(Param node, Object data){
    return node.value;
  }

  public Object visit(StatementBlock node, Object data){
    return node.value;
  }

  public Object visit(IfElseStatement node, Object data){
    return node.value;
  }

  public Object visit(WhileStatement node, Object data){
    return node.value;
  }


  public Object visit(Skip node, Object data){
    return null;
  }




}
