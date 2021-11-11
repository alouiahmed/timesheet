pipeline {
    agent any
    environment { 
        registry = "1710789/timesheet" 
        registryCredential = 'dockerHub'
        dockerImage = '' 
       
    }
    stages {
        stage ('GIT') {
            steps {
                echo "Getting Project from Git";
                git  branch: 'aymen_ouerghui',
    url: 'https://github.com/alouiahmed/timesheet/' ;
                    }
            }
        stage ('MVN CLEAN') {
            steps {
             
                echo "Maven Clean";
                bat 'mvn clean';
                    }
                
            }
        stage ('MVN TEST') {
            steps {
             
                echo "Maven Test JUnit";
                bat 'mvn test';
                }
                
            }
        stage('SONAR') {
            steps{
                echo "SONAR test" ; 
                bat 'mvn sonar:sonar';
                }
            }
                 
        stage ('NEXUS DEPLOIEMENT'){
            steps{
                echo "Nexus"
                bat 'mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=timesheet -Dversion=0.0.6-SNAPSHOT  -DgeneratePom=true -Dpackaging=jar  -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-snapshots/ -Dfile=target/timesheet-0.0.6-SNAPSHOT.jar';
            }
        }
           stage ('Email notification') {
            
            steps{
            emailext attachLog: true, body: 'Logs', subject: 'Timesheet logs', to: 'aymenouerghui17@gmail.com'

            }            
        }
           stage('Building Docker Image'){
                steps{
                    script{
                        dockerImage = docker.build registry + ":$BUILD_NUMBER"
                    }
                }               
            }

            stage('Pushing Docker Images'){
                steps{
                    script{
                        docker.withRegistry( '', registryCredential ) 
                        {dockerImage.push()}
                    }
                }
            }
            
        
}
}