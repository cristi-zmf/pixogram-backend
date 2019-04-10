node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/cristi-zmf/pixogram-backend.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.
      mvnHome = tool 'M3'
   }

   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
//
//   stage('Integration tests') {
//      if (isUnix()) {
//         sh "'${mvnHome}/bin/mvn' test integration-test"
//      } else {
//         bat(/"${mvnHome}\bin\mvn" test integration-test"/)
//      }
//   }


   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }

   stage('Deploy') {
       if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' spring-boot:run -Dspring-boot.run.profiles=DATASET -Dspring-boot.run.arguments=\"--server.port=8085\""
      } else {
         bat(/"${mvnHome}\bin\mvn" spring-boot:run -Dspring-boot.run.profiles=DATASET -Dspring-boot.run.arguments="--server.port=8085"/)
      }
   }
}