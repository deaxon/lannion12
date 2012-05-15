module ApplicationHelper
  def title(page_title, show_title = true)
    content_for(:title) { h(page_title.to_s) }
    @show_title = show_title
  end

  def show_title?
    @show_title
  end

  def stylesheet(*args)
    content_for(:head) { stylesheet_link_tag(*args) }
  end

  def javascript(*args)
    content_for(:head) { javascript_include_tag(*args) }
  end
  def selected_class (but)
    case but
      when 'home'
        return "selected" if controller.controller_name == "home" and controller.action_name == "index" or controller.action_name == "format" or controller.action_name == "project"
     when 'sessions'
        return "selected" if controller.controller_name =="sessions" and controller.action_name == "index" or controller.action_name == "login"
    end
  end
end