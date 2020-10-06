import React from 'react';
import '../../App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import config from '../../Constants';

class AddService extends React.Component {

    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    showErrorModal(msg) {

        const node = document.getElementById('errorMessage');
        node.innerHTML = msg;
        node.classList.remove('d-none');

    }

    showServiceCreated(msg) {

        const node = document.getElementById('successMessage');
        node.innerHTML = msg;
        node.classList.remove('d-none');

    }

    handleLoginResponse(resp) {

        if (typeof resp.error != "undefined") {
            this.showErrorModal(resp.error);
        } else {
            this.showServiceCreated("Service is created");
            window.location = "admin-dashboard"
        }

    }

    handleSubmit(event) {

        event.preventDefault();
        var aid = document.getElementById('admin-id').value;
        var type = document.getElementById('type').value;
        var name = document.getElementById('name').value;
        var desc = document.getElementById('description').value;

        const data = encodeURI('auth-token=' + localStorage.getItem('auth_token') + '&admin-id=' + aid + "&type=" + type + "&name=" + name + "&description=" + desc);

        fetch(config.APP_URL + 'service/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: data
        })
            .then(res => res.json())
            .then(res => this.handleLoginResponse(res))

    }

    render() {

        return (


            <Form className="login-form" onSubmit={this.handleSubmit}>
                <h1 className="font-weight-bold" id="heading">Add service</h1>
                <div className="alert alert-danger d-none" id="errorMessage">
                </div>
                <div className="alert alert-success d-none" id="successMessage"></div>

                <FormGroup>
                    <Label>Admin ID</Label>
                    <Input type="text" id="admin-id" name="admin-id" placeholder="Admin ID" ref={node => (this.adminid = node)}></Input>
                </FormGroup>

                <FormGroup>
                    <Label>Type</Label>
                    <Input type="text" id="type" name="type" placeholder="Type" ref={node => (this.type = node)}></Input>
                </FormGroup>

                <FormGroup>
                    <Label>Name</Label>
                    <Input type="text" id="name" name="name" placeholder="Name" ref={node => (this.name = node)}></Input>
                </FormGroup>

                <FormGroup>
                    <Label>Description</Label>
                    <Input type="textarea" id="description" name="description" placeholder="Description" ref={node => (this.description = node)}></Input>
                </FormGroup>

                <Button className="btn-lg btn-success btn-block mt-4 mb-3">
                    Add Service
                 </Button>


            </Form>
        );

    }

}

export default AddService;
