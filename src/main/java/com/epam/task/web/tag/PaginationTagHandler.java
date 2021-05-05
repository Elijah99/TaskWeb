package com.epam.task.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.ResourceBundle;

public class PaginationTagHandler extends SimpleTagSupport {

    private final static String REPLACEABLE_PART = "##";
    private final static String B_OPEN = "<b>";
    private final static String B_CLOSE = "</b>";
    private final static String A_OPEN = "<a href=\"";
    private final static String TAG_END = "\">";
    private final static String A_CLOSE = "</a>";

    private String uri;
    private int currPage;
    private int itemsCount;
    private int itemsOnPage;
    private int maxLinks = 10;

    @Override
    public void doTag() throws JspException {
        int totalPages = calculateTotalPages();
        if (totalPages <= 1) {
            return;
        }
        int pgStart = Math.max(currPage - maxLinks / 2, 1);
        int pgEnd = pgStart + maxLinks;
        if (pgEnd > totalPages + 1) {
            int diff = pgEnd - totalPages;
            pgStart -= diff - 1;
            if (pgStart < 1) {
                pgStart = 1;
            }
            pgEnd = totalPages + 1;
        }

        constructPagination(totalPages, pgStart, pgEnd);

    }

    private void constructPagination(int totalPages, int pgStart, int pgEnd) throws JspException {
        Writer out = getJspContext().getOut();
        try {
            if (currPage > 1) {
                out.write(constructLink(currPage - 1, "Next"));
            }
            for (int i = pgStart; i < pgEnd; i++) {
                if (i == currPage) {
                    out.write(B_OPEN + i + B_CLOSE);
                } else {
                    out.write(constructLink(i));
                }
            }
            if (!(currPage == totalPages)) {
                out.write(constructLink(currPage + 1, "Next"));
            }

        } catch (IOException e) {
            throw new JspException("Error in Pagination tag", e);
        }
    }


    private int calculateTotalPages() {
        if (itemsCount % itemsOnPage == 0) {
            if (itemsCount / itemsOnPage == 0) {
                return 1;
            } else {
                return itemsCount / itemsOnPage;
            }

        } else {
            return itemsCount / itemsOnPage + 1;
        }
    }

    private String constructLink(int page) {
        return constructLink(page, String.valueOf(page));
    }

    private String constructLink(int page, String text) {
        StringBuilder link = new StringBuilder();
        link.append(A_OPEN)
                .append(uri.replace(REPLACEABLE_PART, String.valueOf(page)))
                .append(TAG_END)
                .append(text)
                .append(A_CLOSE);
        return link.toString();
    }

}
