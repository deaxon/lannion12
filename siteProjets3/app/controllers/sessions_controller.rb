class SessionsController < ApplicationController
  before_filter :authorize, :except => [:new , :create]

  def new
    @title = "Login"
  end

   def add_user
     @user = User.new(params[:user])

   end

  def index
    @annees = Annee.all
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @annees }
    end
  end

  def delete_user
    if request.post?
      @user = User.find(params[:id])
      begin
       @user.destroy
       flash[:notice] = "Utilisateur #{@user.name} supprime"
      rescue Exception => e
        flash[:notice] = e.message
      end
    end
    redirect_to(:action => :list_users)
  end

  def create
    user = User.authenticate(params[:session][:name],
                             params[:session][:password])
    if user.nil?
      flash[:notice] = "Combinaison nom/Mot de passe invalide."
      @title = "S'identifier"
      render 'new'
    else
      sign_in user
      redirect_to(:controller => "sessions", :action => "index")
      flash[:notice] = "Connecte"
    end
  end

  def list_users
    @users = User.all
  end

  def logout
    sign_out
    redirect_to root_path
    flash[:notice] = "Deconnecte"
  end
end