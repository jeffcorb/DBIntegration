#!/bin/zsh

rm -f dbintegration.mv.db
echo "========================================"
echo " DB Integration Automation Challenge "
echo "========================================"

echo "Initializing database..."
mvn compile exec:java -Dexec.mainClass="db.DatabaseInitializer"

echo "Running tests..."
mvn clean test

echo "Generating report..."
mvn surefire-report:report-only

echo "Opening report..."
open target/site/surefire-report.html
