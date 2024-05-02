import * as Yup from 'yup';
// fullName: '',
// userName: '',
// email: '',
// phone: '',
// pass: '',
// pass2: '',
// gender: '',
export const signUpSchema = Yup.object({
    fullName: Yup.string().min(2).max(50).required("please enter Your Name"),
    userName:Yup.string().min(2).max(29).required("please enter User Name"),
    email:Yup.string().email().required("please your email"),
    pass: Yup.string().min(6).max(20).required("please enter you password"),
    phone:Yup.string().min(9).max(12).required("please enter your phone number"),
    pass2: Yup.string().oneOf([Yup.ref("pass"),null],"passwords must match").min(6).max(20).required("please confirm password"),
    gender: Yup.mixed().oneOf(["male", "female"]).required("Please select your gender"),
})