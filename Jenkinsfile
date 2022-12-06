def remote = [:]
remote.name = 'onenet'
remote.host = 'onenet-NGSI-LD-server.eurodyn.com'
remote.allowAnyHosts = true
node
{
    try{
        stage 'Clone the project'
        git branch: 'master',
        url: 'https://{token}@github.com/HeliasEurodyn/sofia.git'
        dir('')
        {
            stage("Build")
            {
                sh "chmod +x mvnw"
                sh "./mvnw clean install -DskipTests"
            }
            stage("Run Unit Tests")
            {
                try {
                    sh "./mvnw test"
                } catch(err) {
                    throw err
                }
            }
            stage("Deploy")
            {
                withCredentials([usernamePassword(credentialsId: 'onenet', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')])
                {
                    remote.user = USERNAME
                    remote.password = PASSWORD
                    sshCommand remote: remote, command: "cd /home/engonenet/ed/onenet_for_jenkins/backend-container;docker-compose down"
                    sshRemove remote: remote, path: '/home/engonenet/ed/onenet_for_jenkins/backend-container/sofia-backend/sofia-0.0.1.jar'
                    sshPut remote: remote, from: './target/sofia-0.0.1.jar', into: '/home/engonenet/ed/onenet_for_jenkins/backend-container/sofia-backend/'
                    sshCommand remote: remote, command: "cd /home/engonenet/ed/onenet_for_jenkins/backend-container;docker-compose up --build -d"
                }
            }
        }

        emailext body: '$PROJECT_NAME - Build # $BUILD_NUMBER - SUCESSS: <br> Check console output at $BUILD_URL to view the results.',
                 subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - SUCESSS!',
                 to: '$DEFAULT_RECIPIENTS'

    }catch(e){
        emailext body: '$PROJECT_NAME - Build # $BUILD_NUMBER - ERROR: <br> Check console output at $BUILD_URL to view the results.',
                 subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - ERROR!',
                 to: '$DEFAULT_RECIPIENTS'
    }

}