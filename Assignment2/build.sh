jjtree ccalparse.jjt
echo --- jjtree run ---
sleep 1

javacc ccalparse.jj
echo --- javacc run ---
sleep 1

javac *.java
echo --- javac run ---
sleep 2

java ccalparse tests/main1.ccl
echo --- java run ---
