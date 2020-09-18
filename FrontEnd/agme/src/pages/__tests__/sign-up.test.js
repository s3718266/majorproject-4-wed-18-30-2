import React from "react";
import { mount } from "enzyme";
import ReactDOM from 'react-dom';
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import renderer from 'react-test-renderer';



//? Pages
import SignUp from '../sign-up';

Enzyme.configure({ adapter: new Adapter() });

describe('Sign-up page test', () => {
    //? 1. Check if Sign-up Page Render Properly
    test('renders learn react link', () => {
        const div = document.createElement('div');
        ReactDOM.render(<SignUp />, div);
    });
    //? 2. Check if there is any changes on sign-up pages
    it('Sign-up snapshot test', () => {
        const component = renderer.create(<SignUp />);
        const tree = component.toJSON();
        expect(tree).toMatchSnapshot();
    })
    //? 3. Test the firstname form input
    it('Firstname form input', () => {
        const component = mount(<SignUp />);
        const fname = component.find("input#firstname");
        fname.instance().value = 'Aldo';
        fname.simulate('change');
        expect(fname.instance().value).toEqual("Aldo");
    })
    //? 4. Check if there is option 
    it("Show option is available in SignUp", () => {
        const component = mount(<SignUp />);
        expect(component.find('select').length).toBe(1);
    })

})
