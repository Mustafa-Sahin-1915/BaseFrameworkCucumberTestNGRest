pipeline{
    agent any
    environment {
        //Project Configurations
        reportUrl = "http://localhost:8080/job/$env.JOB_NAME/$env.BUILD_NUMBER/cucumber-html-reports/overview-failures.html"
    }
    stages{
        stage('compile'){
            steps{
                withMaven(maven:'maven3'){
                    bat "mvn clean compile"
                }
            }
        }
        stage('test'){
            steps{
                withMaven(maven:'maven3'){
                    bat "mvn test"
                }
            }
        }
        stage('cucumber reports'){
            steps{
                cucumber buildStatus:"UNSTABLE",
                         fileIncludePattern: '**/*.json',
                         jsonReportDirectory: 'target/json-reports'

            }
        }
    }
    post {
      	     failure {
      	      echo "Test failed"
                          cucumber buildStatus: 'FAIL',
                                       failedFeaturesNumber: 1,
                                       failedScenariosNumber: 1,
                                       skippedStepsNumber: 1,
                                       failedStepsNumber: 1,
                                       jsonReportDirectory: 'target/json-reports',
                                       fileIncludePattern: '**/*.json',
                                       sortingMethod: 'ALPHABETICAL'

      	     }
      	      success {
              echo "Test succeeded"
                         cucumber buildStatus: 'SUCCESS',
                                                failedFeaturesNumber: 0,
                                                failedScenariosNumber: 0,
                                                skippedStepsNumber: 0,
                                                failedStepsNumber: 0,
                                                jsonReportDirectory: 'target/json-reports',
                                                fileIncludePattern: '**/*.json',
                                                sortingMethod: 'ALPHABETICAL'
              }
              always{
                junit '**/surefire-reports/*.xml'
              }

      }

}