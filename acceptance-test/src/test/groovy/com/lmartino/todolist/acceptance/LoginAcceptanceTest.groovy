package com.lmartino.todolist.acceptance

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class LoginAcceptanceTest extends Specification {

    @Shared
    def client

    def setupSpec() {
        client = new RESTClient(System.getProperty("rest.url"))
        client.handler.failure = {
            resp, reader -> [status: resp.status, data: reader]
        }
    }

    def "User can register with username and password" (){
        given: "New user 'test' with password 'pwd123'"
        def username = 'test'
        def password = 'pwd123'

        when: "User register"
        def response = client.post(path: "/todolist/rest/users/register",
                body: [username: username, password: password],
                requestContentType:'application/json'
        )

        then: "User can see successful operation"
        response.status == 201
        response.data.id != null
        response.username == username


    }


}
