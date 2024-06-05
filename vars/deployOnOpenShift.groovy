#!/usr/bin/env groovy
// OpenShiftCredentialsID 'Service Account Token'
def call(String SERVICE_ACCOUNT_TOKEN, String imageName, String openshiftServer, String openshiftProject) {
    
    // Update deployment configuration with new Docker Hub image tag
    sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' java-deployment.yml"

        sh """
            oc login ${openshiftServer} --token=${SERVICE_ACCOUNT_TOKEN}
            oc project ${openshiftProject}  // Specify your project name
            oc apply -f java-deployment.yml
            oc apply -f java-service.yml 
        """
}

// #!/usr/bin/env groovy

// // KubernetesCredentialsID 'KubeConfig file'
// def call(String k8sCredentialsID, String imageName) {
    
//     // Update deployment.yaml with new Docker Hub image
//     sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' java-deployment.yml"

//     // login to k8s Cluster via KubeConfig file
//     withCredentials([file(credentialsId: "${k8sCredentialsID}", variable: 'KUBECONFIG_FILE')]) {
//         sh "export KUBECONFIG=${KUBECONFIG_FILE} && kubectl apply -f myapp-deployment.yml"
//     }
// }
