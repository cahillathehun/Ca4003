jjtree ccalparse.jjt
echo --- jjtree run ---
sleep 1

javacc ccalparse.jj
echo --- javacc run ---
sleep 1

javac *.java
echo --- javac run ---
sleep 2

echo
echo main1.ccl
java ccalparse main1.ccl
echo main2.ccl
java ccalparse main2.ccl
echo main3.ccl
java ccalparse main3.ccl
echo comments.ccl
java ccalparse comments.ccl
echo scopes
java ccalparse scopes.ccl

echo --- java run ---
