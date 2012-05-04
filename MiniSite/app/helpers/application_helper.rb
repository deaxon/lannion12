module ApplicationHelper
  def selected_class (but)
    case but
      when 'home'
        return "selected" if controller.controller_name == "home" and  controller.action_name == "index"
      when 'projects'
        return "selected" if controller.controller_name == "projects" and  controller.action_name == "project"
      when "contacts"
        return "selected" if controller.controller_name == "contacts" and  controller.action_name == "contact"
      else
        return "selected" if controller.controller_name == but
    end
  end
end
