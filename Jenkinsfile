pipeline {	

	agent any 
	environment { 
        registry = "marwenv1/timesheet" 
		registryCredential = 'dockerHub'
        dockerImage = '' 
       
    }
	
	stages{ 
			
			stage('Test & Build'){
				steps{
					bat "mvn clean package"

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
			
			stage ('Email notification') {
            
            steps{
            emailext attachLog: true, body: 'Logs', subject: 'Timesheet logs', to: 'nada.ben.aissaa@gmail.com'

            }            
        	}
			
		    
		    stage('Cleanup Workspace'){
				steps{
					cleanWs()
				}				
			}
			
		

		}
	}