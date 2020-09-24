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
            window.location = "customer-dashboard"
        }

    }

    handleSubmit(event) {

        event.preventDefault();
        const data = new URLSearchParams(new FormData(event.target));

        fetch(config.APP_URL + 'service/add', {
            method: 'POST',
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
                    <Input type="text" name="admin-id" placeholder="Admin ID" ref={node => (this.adminid = node)}></Input>
                </FormGroup>

                <FormGroup>
                    <Label>Type</Label>
                    <Input type="text" name="type" placeholder="Type" ref={node => (this.type = node)}></Input>
                </FormGroup>

                <FormGroup>
                    <Label>Name</Label>
                    <Input type="text" name="name" placeholder="Name" ref={node => (this.name = node)}></Input>
                </FormGroup>

                <FormGroup>
                    <Label>Description</Label>
                    <Input type="textarea" name="description" placeholder="Description" ref={node => (this.description = node)}></Input>
                </FormGroup>

                <Button className="btn-lg btn-success btn-block mt-4 mb-3">
                    Add Service
                 </Button>


            </Form>
        );

    }

}

export default AddService;
