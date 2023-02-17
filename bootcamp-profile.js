import { useEffect, useState } from "react";
import { NavBar } from "../Component/Nav";
import { Footer } from "../Component/Footer";
import { Button, Modal } from 'react-bootstrap';
import { Registor } from "../Component/Registration";
import axios from "axios";
import AuthServices from "../Services/AuthServices";
import React from 'react';
import "../index.css"
import {
    MDBCol,
    MDBContainer,
    MDBRow,
    MDBCard,
    MDBCardText,
    MDBCardBody,
    MDBCardImage,
    MDBBtn,
    MDBBreadcrumb,
    MDBBreadcrumbItem,
    MDBProgress,
    MDBProgressBar,
    MDBIcon,
    MDBListGroup,
    MDBListGroupItem,
    MDBCardHeader,
    MDBAccordion,
    MDBAccordionHeader,
    MDBAccordionBody,
    MDBAccordionItem
} from 'mdb-react-ui-kit';

// import AxiosClient from "../Services/AxiosClient";


function Profile() {

    const [edit, setEdit] = useState(false)

    const handleEdit=(e)=>{
        setEdit(!edit)

    }

    const handleChnage = (e)=>{
        alert(e.target.id);

        // will this be necessary?
        // if (activeItem === index) {
        //   setActiveItem(null);
        // } else {
        //   setActiveItem(index);
        // }
    }
    return (
        <>
            <section className="profileBody" style={{ backgroundColor: '#eee' }}>


                <MDBContainer className="py-5">
                    <MDBRow>
                        <MDBCol>
                            <MDBBreadcrumb className="bg-light rounded-3 p-3 mb-4">
                                <MDBBreadcrumbItem>
                                    <a href='#'>Home</a>
                                </MDBBreadcrumbItem>
                                {edit==true?<> <MDBBreadcrumbItem active>Edit the user profile</MDBBreadcrumbItem></>:<>
                                    <MDBBreadcrumbItem active>User Profile</MDBBreadcrumbItem></>}
                            </MDBBreadcrumb>
                        </MDBCol>
                        <MDBCol>
                            <Button value="Edit" onClick={handleEdit}>Edit</Button>
                        </MDBCol>
                    </MDBRow>
                    {edit == true ? <>
                        <MDBRow>
                            <MDBCol lg="4">
                                <MDBCard className="mb-4">
                                    <MDBCardBody className="text-center">
                                        <MDBCardImage
                                            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
                                            alt="avatar"
                                            className="rounded-circle"
                                            style={{ width: '150px' }}
                                            fluid />
                                        <label for="formFileLg" class="form-label"> </label>
                                        <input class="form-control form-control-lg" onChange={handleChnage} id="formFileLg" type="file" />
                                        <p className="text-muted mb-1">Full Stack Developer | Participant</p>
                                        <p className="text-muted mb-4">Bay Area, San Francisco, CA</p>
                                        <div className="d-flex justify-content-center mb-2">
                                            <MDBBtn>Follow</MDBBtn>
                                            <MDBBtn outline className="ms-1">Message</MDBBtn>
                                        </div>
                                    </MDBCardBody>
                                </MDBCard>

                                <MDBCard className="mb-4 mb-lg-0">
                                    <MDBCardBody className="p-0">
                                        <MDBListGroup flush className="rounded-3">
                                            <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                                                <MDBIcon fas icon="globe fa-lg text-warning" />
                                                {/* <MDBCardText>https://mdbootstrap.com</MDBCardText> */}
                                                <input type="text" className="text-muted" onChange={handleChnage} id="phone"></input>
                                            </MDBListGroupItem>
                                            <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                                                <MDBIcon fab icon="github fa-lg" style={{ color: '#333333' }} />
                                                {/* <MDBCardText>mdbootstrap</MDBCardText> */}
                                                <input type="text" className="text-muted" onChange={handleChnage} id="phone"></input>
                                            </MDBListGroupItem>
                                            <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                                                <MDBIcon fab icon="twitter fa-lg" style={{ color: '#55acee' }} />
                                                <input type="text" className="text-muted" onChange={handleChnage} id="phone"></input>
                                                {/* <MDBCardText>@mdbootstrap</MDBCardText> */}
                                            </MDBListGroupItem>
                                            <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                                                <MDBIcon fab icon="instagram fa-lg" style={{ color: '#ac2bac' }} />
                                                {/* <MDBCardText>mdbootstrap</MDBCardText> */}
                                                <input type="text" className="text-muted" onChange={handleChnage} id="phone"></input>
                                            </MDBListGroupItem>
                                            <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                                                <MDBIcon fab icon="facebook fa-lg" style={{ color: '#3b5998' }} />
                                                <input type="text" className="text-muted" onChange={handleChnage} id="phone"></input>
                                                {/* <MDBCardText>mdbootstrap</MDBCardText> */}
                                            </MDBListGroupItem>
                                        </MDBListGroup>
                                    </MDBCardBody>
                                </MDBCard>
                            </MDBCol>
                            <MDBCol lg="8">
                                <MDBCard className="mb-4">
                                    <MDBCardBody>
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Full Name</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">

                                                <input type="text" className="text-muted" onChange={handleChnage} id="firstName"></input>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Email</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <input type="text" className="text-muted" onChange={handleChnage} id="email"></input>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Phone</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <input type="text" className="text-muted" onChange={handleChnage} id="phone"></input>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Education</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <input type="text" className="text-muted" onChange={handleChnage} id="education"></input>

                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Skills</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <input type="text" className="text-muted" onChange={handleChnage} id="skills"></input>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Experience</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <input type="text" className="text-muted" onChange={handleChnage} id="experience"></input>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Projects</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <input type="text" className="text-muted" onChange={handleChnage} id="projects"></input>
                                            </MDBCol>
                                        </MDBRow>

                                    </MDBCardBody>
                                </MDBCard>



                                {/* making table dynamic */}

                                <div className="table-responsive bg-white">
                                    <MDBCard>
                                        <MDBCardHeader>
                                            <h4>Experience</h4>
                                        </MDBCardHeader>
                                        <MDBCardBody>
                                            <MDBAccordion>
                                                {data.map((row, index) => (
                                                    <MDBAccordionItem key={row.id}>
                                                        <MDBAccordionHeader onChange={() => handleChnage(index)}>
                                                            <table>
                                                                <thead>
                                                                <tr>
                                                                    <th scope="col">POSITION</th>
                                                                    <th scope="col">CONTACTS</th>
                                                                    <th scope="col">AGE</th>
                                                                    <th scope="col">ADDRESS</th>
                                                                    <th scope="col">SALARY</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <tr>
                                                                    <td>{row.name}</td>
                                                                    <td>{row.email}</td>
                                                                    <td>{row.age}</td>
                                                                    <td>{row.address}</td>
                                                                    <td>{row.salary}</td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </MDBAccordionHeader>
                                                        <MDBAccordionBody>
                                                            hjjjhhdgggh
                                                            gjhgshgyoutdxc
                                                            hjjjvfdds
                                                        </MDBAccordionBody>
                                                    </MDBAccordionItem>
                                                ))}
                                            </MDBAccordion>
                                        </MDBCardBody>
                                    </MDBCard>
                                </div>













                                <MDBRow>
                                    <MDBCol md="6">
                                        <MDBCard className="mb-4 mb-md-0">
                                            <MDBCardBody>
                                                <MDBCardText className="mb-4"><span className="text-primary font-italic me-1">assigment</span> Project Status</MDBCardText>
                                                <MDBCardText className="mb-1" style={{ fontSize: '.77rem' }}>Web Design</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={80} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Website Markup</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={72} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>One Page</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={89} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Mobile Template</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={55} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Backend API</MDBCardText>

                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={66} valuemin={0} valuemax={100} />
                                                </MDBProgress>
                                            </MDBCardBody>
                                        </MDBCard>
                                    </MDBCol>

                                    <MDBCol md="6">
                                        <MDBCard className="mb-4 mb-md-0">
                                            <MDBCardBody>
                                                <MDBCardText className="mb-4"><span className="text-primary font-italic me-1">assigment</span> Project Status</MDBCardText>
                                                <MDBCardText className="mb-1" style={{ fontSize: '.77rem' }}>Web Design</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={80} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Website Markup</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={72} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>One Page</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={89} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Mobile Template</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={55} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Backend API</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={66} valuemin={0} valuemax={100} />
                                                </MDBProgress>
                                            </MDBCardBody>
                                        </MDBCard>
                                    </MDBCol>
                                </MDBRow>
                            </MDBCol>
                        </MDBRow>





                    </> : <>
                        <MDBRow>
                            <MDBCol lg="4">
                                <MDBCard className="mb-4">
                                    <MDBCardBody className="text-center">
                                        <MDBCardImage
                                            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
                                            alt="avatar"
                                            className="rounded-circle"
                                            style={{ width: '150px' }}
                                            fluid />

                                        <p className="text-muted mb-1">Full Stack Developer | Participant</p>
                                        <p className="text-muted mb-4">Bay Area, San Francisco, CA</p>
                                        <div className="d-flex justify-content-center mb-2">
                                            <MDBBtn>Follow</MDBBtn>
                                            <MDBBtn outline className="ms-1">Message</MDBBtn>
                                        </div>
                                    </MDBCardBody>
                                </MDBCard>

                                <MDBCard className="mb-4 mb-lg-0">
                                    <MDBCardBody className="p-0">
                                        <MDBListGroup flush className="rounded-3">
                                            <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                                                <MDBIcon fas icon="globe fa-lg text-warning" />
                                                <MDBCardText>https://mdbootstrap.com</MDBCardText>
                                            </MDBListGroupItem>
                                            <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                                                <MDBIcon fab icon="github fa-lg" style={{ color: '#333333' }} />
                                                <MDBCardText>mdbootstrap</MDBCardText>
                                            </MDBListGroupItem>
                                            <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                                                <MDBIcon fab icon="twitter fa-lg" style={{ color: '#55acee' }} />
                                                <MDBCardText>@mdbootstrap</MDBCardText>
                                            </MDBListGroupItem>
                                            <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                                                <MDBIcon fab icon="instagram fa-lg" style={{ color: '#ac2bac' }} />
                                                <MDBCardText>mdbootstrap</MDBCardText>
                                            </MDBListGroupItem>
                                            <MDBListGroupItem className="d-flex justify-content-between align-items-center p-3">
                                                <MDBIcon fab icon="facebook fa-lg" style={{ color: '#3b5998' }} />
                                                <MDBCardText>mdbootstrap</MDBCardText>
                                            </MDBListGroupItem>
                                        </MDBListGroup>
                                    </MDBCardBody>
                                </MDBCard>
                            </MDBCol>
                            <MDBCol lg="8">
                                <MDBCard className="mb-4">
                                    <MDBCardBody>
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Full Name</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText className="text-muted">Johnatan Smith </MDBCardText>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Email</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText className="text-muted">apuru@edu.com</MDBCardText>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Phone</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText className="text-muted">+237 672.345.678</MDBCardText>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Education</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText className="text-muted">Univeristy of SNVA</MDBCardText>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Skills</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText className="text-muted">JAVA, AWS, React</MDBCardText>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Experience</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText className="text-muted">Senior Level Software Engineer </MDBCardText>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr />
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Projects</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText className="text-muted">Building A Scalable</MDBCardText>
                                            </MDBCol>
                                        </MDBRow>






                                    </MDBCardBody>
                                </MDBCard>

                                <MDBRow>
                                    <MDBCol md="6">
                                        <MDBCard className="mb-4 mb-md-0">
                                            <MDBCardBody>
                                                <MDBCardText className="mb-4"><span className="text-primary font-italic me-1">Education</span> Project Status</MDBCardText>
                                                <MDBCardText className="mb-1" style={{ fontSize: '.77rem' }}>Web Design</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={80} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Website Markup</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={72} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>One Page</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={89} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Mobile Template</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={55} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Backend API</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={66} valuemin={0} valuemax={100} />
                                                </MDBProgress>
                                            </MDBCardBody>
                                        </MDBCard>
                                    </MDBCol>

                                    <MDBCol md="6">
                                        <MDBCard className="mb-4 mb-md-0">
                                            <MDBCardBody>
                                                <MDBCardText className="mb-4"><span className="text-primary font-italic me-1">assigment</span> Project Status</MDBCardText>
                                                <MDBCardText className="mb-1" style={{ fontSize: '.77rem' }}>Web Design</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={80} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Website Markup</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={72} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>One Page</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={89} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Mobile Template</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={55} valuemin={0} valuemax={100} />
                                                </MDBProgress>

                                                <MDBCardText className="mt-4 mb-1" style={{ fontSize: '.77rem' }}>Backend API</MDBCardText>
                                                <MDBProgress className="rounded">
                                                    <MDBProgressBar width={66} valuemin={0} valuemax={100} />
                                                </MDBProgress>
                                            </MDBCardBody>
                                        </MDBCard>
                                    </MDBCol>
                                </MDBRow>
                            </MDBCol>
                        </MDBRow>

                    </>}
                </MDBContainer>


                {/* static table */}

                <div className="table-responsive bg-white">
                    <MDBCard>
                        <MDBCardHeader>
                            <h4>Experience</h4>
                        </MDBCardHeader>
                        <MDBCardBody>
                            <table className="table mb-0">
                                <thead>
                                <tr>
                                    <th scope="col">EMPLOYEE</th>
                                    <th scope="col">POSITION</th>
                                    <th scope="col">CONTACTS</th>
                                    <th scope="col">AGE</th>
                                    <th scope="col">ADDRESS</th>
                                    <th scope="col">SALARY</th>
                                </tr>
                                </thead>
                                <tbody>
                                {data.map((row) => (
                                    <React.Fragment key={row.id}>
                                        <tr>
                                            <th scope="row" style={{ color: '#666666' }}>
                                                {row.name}
                                            </th>
                                            <td>{row.position}</td>
                                            <td>{row.email}</td>
                                            <td>{row.age}</td>
                                            <td>{row.address}</td>
                                            <td>{row.salary}</td>
                                        </tr>
                                        <tr>
                                            <td colSpan="6">
                                                <p>Additional information: {row.additionalInfo}</p>
                                            </td>
                                        </tr>
                                    </React.Fragment>
                                ))}
                                </tbody>
                            </table>
                        </MDBCardBody>
                    </MDBCard>
                </div>


            </section>
        </>
    );
}



export { Profile }