pipeline {	

	agent any 
	
	environment { 
        registry = "alouiahmed/timesheet" 
		registryCredential = 'dockerHub'
        dockerImage = '' 
       
    }

	stages{
			
			stage('Test & Build'){
				steps{
					bat "mvn clean"
					bat "mvn test"
					bat "mvn package"
				}				
			}
			
			stage('Nexus Deploy'){
				steps{
					bat "mvn deploy"
				}				
			}
				
			stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar"
                  }
            }

           
			stage('Building Docker Image'){
				steps{
					script{
						dockerImage = docker.build registry + ":$BUILD_NUMBER"
					}
				}				
			}

			stage('Pushing Docker Image'){
				steps{
					script{
						docker.withRegistry( '', registryCredential ) 
                        {dockerImage.push()}
					}
				}
			}
			
			stage('Cleanup Workspace'){
				steps{
					cleanWs()
				}				
			}
			
		
			
		
			
			

			
		}
	}