When responding to the user's weekly briefing request, instead of returning 
plain text or markdown, return a complete self-contained HTML string that 
renders the briefing as a styled dashboard card (dark theme, table for meetings, 
colored badges for Scheduled/Cancelled status, email summary sections).

The HTML should be injected into an iframe or a div with dangerouslySetInnerHTML 
on the client side so it renders visually instead of as raw text.
