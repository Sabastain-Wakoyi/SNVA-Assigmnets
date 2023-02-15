import { useEffect, useState } from "react";
import { NavBar } from "../Component/Nav";
import { Footer } from "../Component/Footer";
import { Modal} from 'react-bootstrap';
import axios from "axios";
import { Registor } from "../Component/Registration";
import AuthServices from "../Services/AuthServices";

export const Profile = (props) => {
    const [profile, setProfile] = useState(null);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));


    const menuItems = [
        { text: "Home", href: "/" },
        { text: "Contact", href: "/contact" },
        { text: "About Us", href: "/about" },
        { text: "Bootcamps", href: "/bootcamps" },
    ];

//   useEffect(()=>{

//     var data = {};

// var config = {
//   method: 'post',

//   url: 'http://localhost:8077/apiauth/apiprofile',
//   headers: {
//     'Content-Type': 'application/json',
//     'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJva29uQGdtYWlsLmNvbSIsInJvbGVzIjpbXSwiZXhwIjoxNjc3MzQ5NDMwfQ.1XklU-FRyUHCaoPGetXfjVLld00MSnvX_mJjiRlJIqgF7tMXKIV1ex5C8eXve63dbIUxrk-Ngj2pkQ4CsdEihw'
//   },
//   data : data
// };

// axios(config)
// .then(function (response) {
//    console.log("TESTING")
//   console.log(JSON.stringify(response.data));
//   setProfile(response.data)

// })
// .catch(function (error) {
//   console.log("TESTING error", error);
// });


//   },[])

    useEffect(() => {
            // alert('inside the effect')
            let token = ''
            let localUser = AuthServices.getCurrentUser()//localStorage.getItem("user")
            try {
                token = JSON.parse(localUser).response;
            } catch (error) {
                console.log("Axios Error", error)

            }
            const options = {
                method: 'POST',

                url: 'http://localhost:8077/apiauth/apiprofile',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                data: '{}'
            };
            axios.request(options).then(function (response) {
                console.log("testing the response",response.data)
                setProfile(response.data)
            }).catch(function (error) {
                console.error("Testing Error",error);
            });
        }
        , [profile])


    console.log("user", profile);

    return (
        <>
            <NavBar user={user} handleClose={handleClose} handleShow={handleShow} items={menuItems}></NavBar>
            {
                <section className="vh-100" style={{ backgroundColor: "#eee" }}>
                    <div className="container py-5 h-100">
                        <div className="row d-flex justify-content-center align-items-center h-100">
                            <div className="col-md-12 col-xl-4">
                                <div className="card" style={{ borderRadius: "15px" }}>
                                    {
                                        profile!=null?<>
                                            <div className="card-body text-center">
                                                <div className="mt-3 mb-4">
                                                    <img
                                                        src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava2-bg.webp"
                                                        className="rounded-circle img-fluid"
                                                        style={{ width: "100px" }}
                                                    />
                                                </div>
                                                <h4 className="mb-2">{profile.fullName}</h4>
                                                <p className="text-muted mb-4">
                                                    {profile.roles.length>0?<>{profile.roles[0].role}</>:<></>}
                                                    <span className="mx-2">|</span>{" "}
                                                    <a href="#!">{profile.email}</a>
                                                </p>
                                                <div className="mb-4 pb-2">
                                                    <button
                                                        type="button"
                                                        className="btn btn-outline-primary btn-floating"
                                                    >
                                                        <i className="fab fa-facebook-f fa-lg"></i>
                                                    </button>
                                                    <button
                                                        type="button"
                                                        className="btn btn-outline-primary btn-floating"
                                                    >
                                                        <i className="fab fa-twitter fa-lg"></i>
                                                    </button>
                                                    <button
                                                        type="button"
                                                        className="btn btn-outline-primary btn-floating"
                                                    >
                                                        <i className="fab fa-skype fa-lg"></i>
                                                    </button>
                                                </div>
                                                <button
                                                    type="button"
                                                    className="btn btn-primary btn-rounded btn-lg"
                                                >
                                                    Message now
                                                </button>
                                                <div className="d-flex justify-content-between text-center mt-5 mb-2">
                                                    <div>
                                                        <p className="mb-2 h5">8471</p>
                                                        <p className="text-muted mb-0">Wallets Balance</p>
                                                    </div>
                                                    <div className="px-3">
                                                        <p className="mb-2 h5">8512</p>
                                                        <p className="text-muted mb-0">Income amounts</p>
                                                    </div>
                                                    <div>
                                                        <p className="mb-2 h5">4751</p>
                                                        <p className="text-muted mb-0">Total Transactions</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </>:<>No Data Found</>
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            }
            <Modal show={show} onHide={handleClose} backdrop="static" keyboard={false}>
                <Modal.Header closeButton>
                </Modal.Header>
                <Modal.Body>
                    <Registor></Registor>
                </Modal.Body>
            </Modal>
            <Footer></Footer>
        </>
    );
};
