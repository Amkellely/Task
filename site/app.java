const express = require('express');
        const nodemailer = require('nodemailer');
        const app = express();
        const port = 3000;

        const transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
        user: 'your_email@gmail.com',
        pass: 'your_email_password'
        }
        });

        app.post('/create-task', (req, res) => {
        // Логика создания задачи в базе данных

        const mailOptions = {
        from: 'your_email@gmail.com',
        to: 'assignee_email@example.com',
        subject: 'New Task Assigned',
        text: 'A new task has been assigned to you. Click the link to view details: http://yourapp.com/tickets'
        };

        transporter.sendMail(mailOptions, (error, info) => {
        if (error) {
        console.error(error);
        } else {
        console.log('Email sent: ' + info.response);
        }
        });

        res.redirect('/tickets');
        });

        app.listen(port, () => {
        console.log(`Server is running at http://localhost:${port}`);
        });
