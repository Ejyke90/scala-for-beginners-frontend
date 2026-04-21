When responding to the user's weekly briefing request, instead of returning 
plain text or markdown, return a complete self-contained HTML string that 
renders the briefing as a styled dashboard card (dark theme, table for meetings, 
colored badges for Scheduled/Cancelled status, email summary sections).

The HTML should be injected into an iframe or a div with dangerouslySetInnerHTML 
on the client side so it renders visually instead of as raw text.


Act as a Senior UI/UX Designer. Analyze the attached screenshot of my 'Email Stats' dashboard. The current layout is too narrow and the background images are distracting. Please provide CSS and layout recommendations to:
Expand the Container: Transition from a narrow fixed-width container to a responsive 'Max-Width' layout (e.g., 1200px) that utilizes more screen real estate.
Refine Borders & Cards: Replace the heavy container borders with subtle box-shadows or thin 1px borders in a light gray (#E2E8F0) to create a 'card' feel.
Background Treatment: Suggest ways to minimize background distraction—either by using a solid neutral color or applying a heavy Gaussian blur and a dark/light overlay to the current mountain image.
Improve Information Density: Reorganize the 'Email Stats' into a more cohesive grid and make the 'External Domains' list more scannable with better row padding and hover states
