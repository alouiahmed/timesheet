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
			
			
				
			stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar"
                  }
            }

           
			stage('Building Docker Image'){
				steps{
					script{
						dockerImage = docker.build registry + ":$BUILD_NUMBER" --force
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
