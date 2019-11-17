import React, {FunctionComponent ,useState, useEffect} from 'react'

interface Props {
    // No props are passed in
}

const Notifications:FunctionComponent<Props> = ({}) => {
    const eventUrl = "http://localhost:9080/notification-service/notifications/stream";
    const [notification, setNotification] = useState("N/A");
    const [eventSource, _] = useState(new EventSource(eventUrl));
    
    useEffect(() => {
        eventSource.addEventListener("message", message => {
            console.log(message);
            setNotification(message.data);
        });
    }, [notification,eventSource])
    
    return (
        <div>
            {notification}
        </div>
    )
}

export default Notifications
