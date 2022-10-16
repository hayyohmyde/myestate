import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import './index.css';
import Header from './pages/components/Header';
import HomePage from './pages/HomePage';
import LoginSignUp from './pages/LoginSignUp';
import ContactUs from './pages/ContactUs';
import NoPage from './pages/NoPage';
import reportWebVitals from './reportWebVitals';
import Dashboard from './pages/Dashboard';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Header />}>
          <Route index element={<HomePage />} />
          {/* <Route path="home" element={<HomePage />} /> */}
          <Route path="login" element={<LoginSignUp />} >
            <Route path="dashbord" element={<Dashboard />} />
          </Route>
          <Route path="contact" element={<ContactUs />} />
          <Route path="*" element={<NoPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
