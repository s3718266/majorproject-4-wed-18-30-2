import React, { useState } from "react";
import '../../App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';

import Modal from "react-modal";

Modal.setAppElement("#root");

export default function Booking() {

    var selectedService = 0;
    const [isOpen, setIsOpen] = useState(false);

    function toggleModal() {
        setIsOpen(!isOpen);
    }

    function renderOptions() {
    
    }

    function handleService(e) {
        selectedService = e.target.value;
    }

    function renderWorkers() {

        let serviceId = selectedService;

    }

    return (
        <div className="App">

            <Button onClick={toggleModal}>Book a service</Button>

            <Modal
                isOpen={isOpen}
                onRequestClose={toggleModal}
                contentLabel="My dialog"
            >
                <Form className="login-form">

                    <h1 className="font-weight-bold" id="heading">Booking</h1>
                    <div className="alert alert-danger d-none" id="errorMessage">
                    </div>

                    <FormGroup>
                        <Label>Date and Time</Label>
                        <Input type="date" name="datetime" placeholder="Date and Time"></Input>
                    </FormGroup>

                    <FormGroup>
                        <Label for="service">Services</Label>
                        <Input type="select" name="service" id="service" onChange={handleService}>
                            {renderOptions()}
                        </Input>
                    </FormGroup>                 

                    <FormGroup>
                        <Label for="worker">Worker</Label>
                        <Input type="select" name="worker" id="worker">
                            {renderWorkers()}
                        </Input>
                    </FormGroup>

                    <Button className="btn-lg btn-success btn-block mt-5 mb-3" type="submit">
                        Book
                    </Button>

                </Form>
                <Button onClick={toggleModal}>
                    Close modal
                </Button>
            </Modal>
        </div>
    );
}